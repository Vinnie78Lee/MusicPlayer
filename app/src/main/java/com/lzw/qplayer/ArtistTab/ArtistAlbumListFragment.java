package com.lzw.qplayer.ArtistTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lzw.qplayer.Album;
import com.lzw.qplayer.AlbumActivity;
import com.lzw.qplayer.Artist;
import com.lzw.qplayer.MusicDB;
import com.lzw.qplayer.R;


public class ArtistAlbumListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_albumlist,container,false);
        ListView listView = (ListView) view.findViewById(R.id.list_artist_albumlist);
        listView.setNestedScrollingEnabled(true);
        final Artist artist = MusicDB.getInstance(getActivity()).findArtistByName(getActivity().getIntent().getStringExtra("artistName"));
        ArtistAlbumListAdapter adapter = new ArtistAlbumListAdapter(getActivity(),artist.getAlbumlist());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                Album album = MusicDB.getInstance(getActivity()).findAlbumById(artist.getAlbumlist().get(position));
                intent.putExtra("albumName", album.getName());
                startActivity(intent);
            }
        });
        return view;
    }
}
