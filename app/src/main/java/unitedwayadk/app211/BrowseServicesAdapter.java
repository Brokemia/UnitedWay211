package unitedwayadk.app211;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class BrowseServicesAdapter extends RecyclerView.Adapter<BrowseServicesAdapter.ServiceViewHolder> {
    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;

        ServiceViewHolder(final BrowseServicesActivity activity, View view) {
            super(view);

            containerView = view.findViewById(R.id.service_row);
            textView = view.findViewById(R.id.service_row_text_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ViewServiceActivity.class);
                    intent.putExtra("name", textView.getText().toString());
                    intent.putExtra("desc", ((Service)v.getTag()).description);
                    intent.putExtra("phoneNumber", ((Service)v.getTag()).phoneNumber);
                    intent.putExtra("phone2Label", ((Service)v.getTag()).phone2Label);
                    intent.putExtra("phone2", ((Service)v.getTag()).phone2);
                    intent.putExtra("website", ((Service)v.getTag()).website);
                    intent.putExtra("address", ((Service)v.getTag()).address);
                    activity.startActivity(intent);
                }
            });
        }
    }

    private List<Service> services = new ArrayList<>();
    private Context context;
    private BrowseServicesActivity activity;

    BrowseServicesAdapter(BrowseServicesActivity activity, Context context) {
        this.context = context;
        this.activity = activity;
        loadServices();
    }

    public void loadServices() {
        InputStream is = null;
        try {
            is = context.getAssets().open("service_list.tsv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            // Skip header
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split("\t");
                Service service = new Service();
                service.name = row[0];
                service.description = row[1];
                service.phoneNumber = row[2];
                service.phone2Label = row[3];
                service.phone2 = row[4];
                service.website = row[5];
                service.address = row[6];
                service.serviceType = ServiceType.valueOf(row[7]);
                service.county = County.valueOf(row[8]);
                if(service.serviceType == MainActivity.serviceType && (service.county == MainActivity.county || service.county == County.All))
                    services.add(service);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if(is != null)
                    is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_row, parent, false);

        return new ServiceViewHolder(activity, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        holder.textView.setText(services.get(position).name);
        holder.containerView.setTag(services.get(position));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}
