package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ItemDecoration;
import com.example.myapplication.adapter.ReplyAdapter;
import com.example.myapplication.model.repModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class Detail_View_Activity extends BaseActivity {
    @BindView(R.id.txttitle)
    public TextView tvtitle;
    @BindView(R.id.txtDesc)
    public TextView tvdescription;
    @BindView(R.id.txtCat)
    public TextView tvcategory;
    @BindView(R.id.bookthumbnail)
    public ImageView img;
    @BindView(R.id.replyView)
    public RecyclerView rcView;

    private ArrayList<repModel> repArray;

    private void init(){
        repArray = new ArrayList<repModel>();
    }

    void getPost(){

    }
    void getRepList(){

    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ReplyAdapter myAdapter = new ReplyAdapter(getApplicationContext(), getListModel());
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(myAdapter);

        ItemDecoration spaceDecoration = new ItemDecoration(10);
        rcView.addItemDecoration(spaceDecoration);
        // Recieve data
        Intent intents = getIntent();
        String Title = intents.getExtras().getString("Title");
        String post_ID = intents.getExtras().getString("post_id");
        int image = intents.getExtras().getInt("Thumbnail") ;
        // Setting values

        tvtitle.setText(Title);
//        tvdescription.setText(Description);
        img.setImageResource(image);
    }

    public List<repModel> getListModel(){
        ArrayList lstTxtModel = new ArrayList<>();
        lstTxtModel.add(new repModel("질문있습니다.","Categorie repModel","테스트작정",1));
        lstTxtModel.add(new repModel("질문있습니다.2","Categorie repModel","테스트임돠",1));
        lstTxtModel.add(new repModel("질문있습니다.3","Categorie repModel","ㅌㅌㅌ3",1));
        lstTxtModel.add(new repModel("질문있습니다.4","Categorie repModel","ㅋㅋㅋ",1));
        lstTxtModel.add(new repModel("질문있습니다.5","Categorie repModel","Dㅇㅇㄻㄴ",1));
        lstTxtModel.add(new repModel("질문있습니다.6","Categorie repModel","D book5",1));
        lstTxtModel.add(new repModel("질문있습니다.7","Categorie repModel","n book6",1));
        return lstTxtModel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_book_2;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
