package com.example.downloadandopenfiles;

import static com.example.downloadandopenfiles.R.*;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    Button button;

    static final String url="https://wallpaper.forfun.com/fetch/3e/3efd2ed5da0b2a02fb9039767d752e2c.jpeg?h=600&r=0.5";
    String urlName="Apple image.jpg";

    String filePath = "Downloads/Hibiscus flower image.jpg";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(id.openBtn);
        button=findViewById(id.downloadBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadBtn(url,urlName);
                openBtn();
            }
        });
    }

    public void downloadBtn(String url,String urlName){

        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        request.setTitle(urlName);
        request.allowScanningByMediaScanner();
        request.setDescription("Downloading "+urlName);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,urlName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


        DownloadManager manager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE );
        manager.enqueue(request);

    }

    public void openBtn()
    {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setDataAndType(Uri.parse(filePath), "*/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the activity
            startActivity(intent);
        } else {
            Toast.makeText(this, "File this is not there", Toast.LENGTH_SHORT).show();
        }
    }

}
