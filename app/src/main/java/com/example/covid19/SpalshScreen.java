package com.example.covid19;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpalshScreen extends Fragment {

    String[] prevention = {
            "Clean your hands often",
            "Clean your phone often",
            "Don’t touch your eyes, nose or mouth",
            "Cover your nose and mouth when you cough or sneeze",
            "Maintain social distancing",
            "Stay home if you feel unwell",
            "Follow the directions of your local health authority"
    };

    TextView preventingTV;
    public SpalshScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spalsh_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preventingTV = view.findViewById(R.id.prentationTV);


        Random random = new Random();
        int n = random.nextInt((prevention.length)-1);

        preventingTV.setText(prevention[n]);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               doWork();
                StartApp();

            }
        });
        thread.start();



    }
    private void StartApp() {

        Navigation.findNavController(getActivity(),R.id.nav_host_fragment_container).navigate(R.id.homeFragment);

    }


    private void doWork() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
