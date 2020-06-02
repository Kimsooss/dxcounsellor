package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Fragment.BaseFragment;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ReplyAdapter;
import com.example.myapplication.model.repModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class DetailFragment extends BaseFragment {

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListModel();
        ReplyAdapter myAdapter = new ReplyAdapter(getContext(), getListModel());
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        rcView.setAdapter(myAdapter);

    }
    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup parentView, Bundle savedInstanceState){
        getListModel();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment02, parentView,false);
        ButterKnife
        Button btn = (Button) rootView.findViewById(R.id.btn02);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentChanged(1);
            }
        });


        ReplyAdapter myAdapter = new ReplyAdapter(getApplicationContext(), getListModel());
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(myAdapter);

        //    ItemDecoration spaceDecoration = new ItemDecoration(1);
        //   rcView.addItemDecoration(spaceDecoration);
        // Recieve data
        Intent intents = getIntent();
        String Title = intents.getExtras().getString("Title");
        String Description = intents.getExtras().getString("Description");
        int image = intents.getExtras().getInt("Thumbnail") ;
        // Setting values

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(image);



        return rootView;
    }*/

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_2;
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

}
