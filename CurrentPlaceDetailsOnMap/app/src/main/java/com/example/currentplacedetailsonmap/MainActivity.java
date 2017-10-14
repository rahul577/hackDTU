package com.example.currentplacedetailsonmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Caption;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public VisionServiceClient visionServiceClient= new VisionServiceRestClient("0bea8746fa9040179804d782c93172aa","https://westcentralus.api.cognitive.microsoft.com/vision/v1.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bitmap myBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.sleeep);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        Button button=(Button)findViewById(R.id.button);
        imageView.setImageBitmap(myBitmap);

        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        final ByteArrayInputStream inputStream=new ByteArrayInputStream(outputStream.toByteArray());


        Button btnProcess=(Button)findViewById(R.id.button);
        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<InputStream,String,String> visionTask= new AsyncTask<InputStream, String, String>() {


                    @Override
                    protected void onPostExecute(String s) {
                        AnalysisResult result=new Gson().fromJson(s,AnalysisResult.class);
                        TextView textView=(TextView)findViewById(R.id.textView);
                        StringBuilder stringBuilder=new StringBuilder();
                        for(Caption caption:result.description.captions){
                            stringBuilder.append(caption.text);
                        }
                        textView.setText(stringBuilder);

                    }

                    @Override
                    protected String doInBackground(InputStream... inputStreams) {
                        try{
                            String[] features={"Description"};
                            String[] details={};

                            AnalysisResult result=visionServiceClient.analyzeImage(inputStreams[0],features,details);
                            String str=new Gson().toJson(result);
                            return str;
                        } catch (Exception e) {
                            return null;
                        }


                    }


                };
                visionTask.execute(inputStream);
            }

        });
    }
}