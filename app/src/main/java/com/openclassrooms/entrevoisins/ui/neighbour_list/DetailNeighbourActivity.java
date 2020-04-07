package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class DetailNeighbourActivity extends AppCompatActivity {

    private ImageButton mBackButton;
    private Neighbour fneighbour;
    private TextView id_nom;
    private ImageView id_avatar;
    private TextView id_nom2;
    private TextView id_facebook;
    private ImageButton imageButtonStar;
    private boolean favorite;
    private NeighbourApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        Intent detailNeighbourActivityIntent = getIntent();
        fneighbour = detailNeighbourActivityIntent.getParcelableExtra("neighbour");
        id_nom = findViewById(R.id.id_nom);
        id_nom.setText(fneighbour.getName());
        id_nom2 = findViewById(R.id.id_nom2);
        id_nom2.setText(fneighbour.getName());
        id_facebook = findViewById(R.id.id_facebook);
        id_facebook.setText("  www.facebook.fr/"+fneighbour.getName());

        id_avatar = findViewById(R.id.id_avatar);
        Picasso.get().load(fneighbour.getAvatarUrl()).into(id_avatar);

        favorite = fneighbour.getFavorite();

        mApiService = DI.getNeighbourApiService();

        mBackButton = findViewById(R.id.backButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageButtonStar = findViewById(R.id.imageButtonStar);
        if(favorite)
        {
            imageButtonStar.setImageResource(R.drawable.ic_star_white_24dp);
        }
        else
        {
            imageButtonStar.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
        imageButtonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorite)
                {
                    favorite = false;
                    fneighbour.setFavorite(false);
                    imageButtonStar.setImageResource(R.drawable.ic_star_border_white_24dp);
                }
                else
                {
                    favorite = true;
                    fneighbour.setFavorite(true);
                    imageButtonStar.setImageResource(R.drawable.ic_star_white_24dp);
                }
                mApiService.setFavoriteNeighbour(fneighbour);
            }
        });
    }
}
