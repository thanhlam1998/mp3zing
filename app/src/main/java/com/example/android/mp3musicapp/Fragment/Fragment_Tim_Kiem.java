package com.example.android.mp3musicapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mp3musicapp.Adapter.SearchMusicAdapter;
import com.example.android.mp3musicapp.Model.BaiHat;
import com.example.android.mp3musicapp.R;
import com.example.android.mp3musicapp.Service.ApiService;
import com.example.android.mp3musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewSearchMusic;
    TextView tvNoDataWhenSearch;
    SearchMusicAdapter searchMusicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppThemeForSearch);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        view = localInflater.inflate(R.layout.fragment_tim_kiem, container, false);
        viewBinding(view);
        return view;
    }

    private void viewBinding(View view) {
        toolbar = view.findViewById(R.id.toolBarSearchMusic);
        recyclerViewSearchMusic = view.findViewById(R.id.recyclerViewSearchMusic);
        tvNoDataWhenSearch = view.findViewById(R.id.tvNoResultForMusic);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tìm kiếm...");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMusicKeyword(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchMusicKeyword(String query){
        DataService service = ApiService.getService();
        Call<List<BaiHat>> callback = service.getSearchMusicList(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                if(baiHatArrayList.size() > 0){
                    searchMusicAdapter = new SearchMusicAdapter(getActivity(), baiHatArrayList);
                    recyclerViewSearchMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerViewSearchMusic.setAdapter(searchMusicAdapter);
                    tvNoDataWhenSearch.setVisibility(View.GONE);
                    recyclerViewSearchMusic.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewSearchMusic.setVisibility(View.GONE);
                    tvNoDataWhenSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
