package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Controller;
import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.model.postModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    @BindView(R.id.swapeLo)
    SwipeRefreshLayout swp;
    private HomeViewModel homeViewModel;
    @BindView(R.id.recyclerview_id)
    RecyclerView myrv;
    RecyclerViewAdapter myAdapter;
    List<postModel> lstTxtModel;
//    public void testset(){
//
//        lstTxtModel = new ArrayList<>();
//        lstTxtModel.add(new TxtModel("질문있습니다.","Categorie TxtModel","Description book",R.drawable.thevigitarian));
//        lstTxtModel.add(new TxtModel("질문2","Categorie TxtModel","Description book",R.drawable.thewildrobot));
//        lstTxtModel.add(new TxtModel("유실물센터","Categorie TxtModel","Description book",R.drawable.mariasemples));
//        lstTxtModel.add(new TxtModel("가나다라","Categorie TxtModel","Description book",R.drawable.themartian));
//        lstTxtModel.add(new TxtModel("He Died with...","Categorie TxtModel","Description book",R.drawable.hediedwith));
//        lstTxtModel.add(new TxtModel("The Vegitarian","Categorie TxtModel","Description book",R.drawable.thevigitarian));
//        lstTxtModel.add(new TxtModel("The Wild Robot","Categorie TxtModel","Description book",R.drawable.thewildrobot));
//    }


    @Override
    public void onStart() {
        super.onStart();
        test(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        lstTxtModel = new ArrayList<postModel>();
        //RecyclerView myrv = (RecyclerView) root.findViewById(R.id.recyclerview_id);
        myAdapter = new RecyclerViewAdapter(this.getContext(), lstTxtModel);
        myrv.setLayoutManager(new GridLayoutManager(this.getContext(),3));
        myrv.setAdapter(myAdapter);
        ButterKnife.bind(this,root);
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                test(false);
            }
        });
        return root;
    }


    void test(boolean flag){
        //String mac = Global.getInstance().usMd.getMacaddr();
            Call<ArrayList<postModel>> res = Controller.getInstance().getService().getPostList("test");
            res.enqueue(new Callback<ArrayList<postModel>>() {
                @Override
                public void onResponse(Call<ArrayList<postModel>> call, Response<ArrayList<postModel>> response) {
                    Log.d("Retrofit", response.toString());
                    if (response.body() != null){
                        if(lstTxtModel.size()>0)
                            lstTxtModel.clear();
                        lstTxtModel.addAll( response.body());
                        myAdapter.notifyDataSetChanged();
                        if(!flag){
                            swp.setRefreshing(false);
                        }
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<postModel>> call, Throwable t) {
                    Log.e("Err", "zzzzz");
                }
            });
    }
}
