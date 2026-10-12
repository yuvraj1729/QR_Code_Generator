package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

import org.w3c.dom.Text;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQRCodeActivity extends AppCompatActivity {

    private TextView QRCodeTV;
    private ImageView QRCodeIV;
    private TextInputEditText DataEdt;
    private Button GenerateQRBtn;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);
        QRCodeTV = findViewById(R.id.idTVGenerateQR);
        QRCodeIV = findViewById(R.id.idIVQRcode);
        DataEdt = findViewById(R.id.idEdtData);
        GenerateQRBtn = findViewById(R.id.idBtnGenerateQR);
        GenerateQRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = DataEdt.getText().toString();
                if (data.isEmpty()){
                    Toast.makeText(GenerateQRCodeActivity.this,"Please Enter Some Data To Generate QR Code", Toast.LENGTH_SHORT);
                }else{
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int dimen = width<height ? width:height;
                    dimen = dimen * 3/4;

                    qrgEncoder = new QRGEncoder(DataEdt.getText().toString(),null, QRGContents.Type.TEXT,dimen);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        QRCodeTV.setVisibility(View.GONE);
                        QRCodeIV.setImageBitmap(bitmap);

                    }catch (WriterException e){
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}