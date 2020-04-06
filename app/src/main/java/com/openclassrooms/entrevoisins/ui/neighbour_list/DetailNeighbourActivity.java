package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class DetailNeighbourActivity extends AppCompatActivity {

    private ImageButton mBackButton;
    private Neighbour neighbour;
    private TextView id_nom;
    private ImageView id_avatar;
    private TextView id_nom2;
    private TextView id_facebook;
    private ImageButton imageButtonStar;
    private boolean favorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        Intent detailNeighbourActivityIntent = getIntent();
        neighbour = detailNeighbourActivityIntent.getParcelableExtra("neighbour");
        id_nom = findViewById(R.id.id_nom);
        id_nom.setText(neighbour.getName());
        id_nom2 = findViewById(R.id.id_nom2);
        id_nom2.setText(neighbour.getName());
        id_facebook = findViewById(R.id.id_facebook);
        id_facebook.setText("  www.facebook.fr/"+neighbour.getName());

        id_avatar = findViewById(R.id.id_avatar);
        Picasso.get().load(neighbour.getAvatarUrl()).into(id_avatar);

        favorite = neighbour.getFavorite();

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
                    imageButtonStar.setImageResource(R.drawable.ic_star_border_white_24dp);
                }
                else
                {
                    favorite = true;
                    imageButtonStar.setImageResource(R.drawable.ic_star_white_24dp);
                }
                neighbour.setFavorite(favorite);
            }
        });
    }
}
