package com.prabidhinepal.mmccomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout thisLayout;
    NavigationView navigationView;
    View sidebar_header_layout;
    TextView sidebar_header_title;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //All getViewById section is done within this function
        initViews();

        //Loads user data and sets the values in the activity
        loadUserInfo();

        //A menu is added in the sidebar
        setNavigationViewMenu();

        //A menu is added to the toolbar
        setToolbarMenu();

        //Listener for click events in toolbar menu
        setToolbarMenuListener();

        //Sidebar menu toggle with toolbar icon
        setNavigationViewToggle();


    }
    private String getUserRole(){return "admin";}
    private void initViews(){
        thisLayout = findViewById(R.id.activity_main_root);
        navigationView = findViewById(R.id.home_navigation_view);
        sidebar_header_layout = navigationView.getHeaderView(0);
        sidebar_header_title = sidebar_header_layout.findViewById(R.id.sidebar_header_title);
        toolbar = findViewById(R.id.toolbar_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }
    private void loadUserInfo(){
        sidebar_header_title.setText("Ashish Neupane");
    }
    private void setNavigationViewMenu(){
        if (getUserRole().equals("admin"))
            navigationView.inflateMenu(R.menu.sidebar_menu_admin);
        navigationView.inflateMenu(R.menu.sidebar_menu_user);
    }
    private void setNavigationViewToggle() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void setToolbarMenu(){
        toolbar.inflateMenu(R.menu.toolbar_menu_main);
    }
    private void setToolbarMenuListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                }
                return true;
            }
        });
    }
}