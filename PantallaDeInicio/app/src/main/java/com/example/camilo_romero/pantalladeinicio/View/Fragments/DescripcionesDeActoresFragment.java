package com.example.camilo_romero.pantalladeinicio.View.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerActor;
import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescripcionesDeActoresFragment extends Fragment {

    public static final String CLAVE_ACTOR = "objeto actores";
    private TextView textViewNombreDelActor;
    private TextView textViewNacionalidadDelActor;
    private TextView textViewFechaDeNacimientoDelActor;
    private ImageView imageViewImagenDelActor;



    public static DescripcionesDeActoresFragment fabricaDeActoresFragment(Actor actor){
        DescripcionesDeActoresFragment descripcionesDeActoresFragment = new DescripcionesDeActoresFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable(CLAVE_ACTOR, actor);

        descripcionesDeActoresFragment.setArguments(bundle);

        return descripcionesDeActoresFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        Actor actor = (Actor) bundle.getSerializable(CLAVE_ACTOR);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_login for this fragment
        View view = inflater.inflate(R.layout.fragment_descripciones_de_actores, container, false);

        textViewNombreDelActor = view.findViewById(R.id.textViewNombreDelActor);
        imageViewImagenDelActor = view.findViewById(R.id.imageViewPosterDelActor);
        textViewNacionalidadDelActor = view.findViewById(R.id.textViewNacionalidadActor);
        textViewFechaDeNacimientoDelActor = view.findViewById(R.id.textViewFechaNacimientoActor);


        Bundle bundle = getArguments();

        Actor actor = (Actor)bundle.getSerializable(CLAVE_ACTOR);


        new ControlerActor().obtenerActorPorID(actor.getId(), new ResultListener<Actor>() {
            @Override
            public void finish(Actor resultado) {
                textViewNombreDelActor.setText(resultado.getName());
                textViewNacionalidadDelActor.setText(resultado.getPlace_of_birth());
                textViewFechaDeNacimientoDelActor.setText(resultado.getBirthday());

            }
        });

        Picasso.with(getContext())
                .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W154 + actor.getProfile_path())
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_not_loaded)
                .into(imageViewImagenDelActor);


        return view;
    }

}
