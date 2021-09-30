package com.example.petowner;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    Button hire_sitter, hire_payment, btn_copy;
    private ImageView image_profile;
    String full_name, address, url, phone_no, nid, availability, price, gmail;
    private ClipboardManager clipboardManager;
    private ClipData clipData;

    public DetailsFragment() {

    }

    public DetailsFragment(String full_name, String address, String url, String phone_no, String nid, String availability, String price, String gmail) {
        this.full_name = full_name;
        this.address = address;
        this.url = url;
        this.phone_no = phone_no;
        this.nid = nid;
        this.availability = availability;
        this.price = price;
        this.gmail = gmail;
    }

    public DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        ImageView holder_profile = v.findViewById(R.id.holder_profile);
        TextView holder_name = v.findViewById(R.id.holder_name);
        TextView holder_address = v.findViewById(R.id.holder_address);
        TextView holder_phone_no = v.findViewById(R.id.holder_phone_no);
        TextView holder_nid = v.findViewById(R.id.holder_nid);
        TextView holder_availability = v.findViewById(R.id.holder_availability);
        TextView holder_price = v.findViewById(R.id.holder_price);
        TextView holder_gmail = v.findViewById(R.id.holder_gmail);

        Button hire_sitter = v.findViewById(R.id.hire_sitter);
        Button hire_payment = v.findViewById(R.id.hire_payment);
        Button btn_copy = v.findViewById(R.id.btn_copy);

        clipboardManager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);


        hire_sitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO_EMAILS = {" "};


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, TO_EMAILS);


                intent.putExtra(Intent.EXTRA_SUBJECT, "PetSitter App");
                intent.putExtra(Intent.EXTRA_TEXT, "You got hired");

                startActivity(Intent.createChooser(intent, "Choose your email client"));
            }
        });

        hire_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent paymentLink = new Intent(Intent.ACTION_VIEW);
                paymentLink.setData(Uri.parse("https://www.paypal.com/signin?returnUri=https%3A%2F%2Fwww.paypal.com%2Fbusinesswallet%2Fmoney&fbclid=IwAR1_2nHIV1T-1FCzMNQzHclGSm3oe_-7aJpoVqKfNoerWZTaSqZSwj3iA1M"));
                startActivity(paymentLink);

            }
        });

        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtcopy = holder_gmail.getText().toString();
                clipData = ClipData.newPlainText("text",txtcopy);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getActivity().getApplicationContext(),"Data Copied to Clipboard", Toast.LENGTH_SHORT).show();

            }
        });


        Picasso.get().load(url).into(holder_profile);

        holder_name.setText(full_name);
        holder_address.setText(address);
        holder_phone_no.setText(phone_no);
        holder_nid.setText(nid);
        holder_availability.setText(availability);
        holder_price.setText(price);
        holder_gmail.setText(gmail);

        return v;

    }

    public void onBackPressed(){
        AppCompatActivity appCompatActivity = (AppCompatActivity)getContext();
        // fragment_search_view id was bar
        appCompatActivity.getSupportFragmentManager().beginTransaction().
                replace(R.id.bar, new ProfileFragment()).addToBackStack(null).commit();
    }
}