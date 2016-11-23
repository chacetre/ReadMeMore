package com.example.charlotte.readmemore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charlotte.readmemore.ListLivres;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;
    private ImageView btn_navigation_drawer;
    private static int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private Button listButton;
    private Button statistiqueButton;
    private Button winButton;
    private TextView infoLectureEnCours;
    private Button suggestionButton;
    private Button notificationButton;

    private ListLivres listLivres = new ListLivres();
    private int NbPageLu = 0;


    // Pour la DB
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SignInButton mSignInButton;
    private TextView mStatusTextView;
    private Button mSignOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btn_navigation_drawer = (ImageView) findViewById(R.id.btn_navigation_drawer);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        listButton = (Button) findViewById(R.id.listButton);
        statistiqueButton = (Button) findViewById(R.id.statistiqueButton);
        suggestionButton = (Button) findViewById(R.id.suggestionButton);
        notificationButton = (Button) findViewById(R.id.notificationButton);
        winButton = (Button) findViewById(R.id.winButton);
        infoLectureEnCours = (TextView) findViewById(R.id.infoLectureEnCours);

        listLivres = recupererLivres();
        NbPageLu = listLivres.size();

        infoLectureEnCours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfosLivreActivity.class);
                startActivity(intent);
            }

        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListGeneralActivity.class);
                intent.putExtra("identifiantListe", (Parcelable) listLivres);
                startActivity(intent);
            }

        });

        statistiqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StatistiqueActivity.class);
                startActivity(intent);
            }

        });

        suggestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SuggestionActivity.class);
                startActivity(intent);
            }

        });

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }

        });

        winButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WinActivity.class);
                intent.putExtra("nbPageLu", NbPageLu);
                startActivity(intent);
            }

        });

        setConnection();
    }

    public ListLivres recupererLivres() {
        // Grace a la dataBase

        /* reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<List<Livre>> genericTypeIndicator = new GenericTypeIndicator<List<Livre>>() {
                };
                bookList = dataSnapshot.getValue(genericTypeIndicator);
                for (RecyclerViewFragment fragment :
                        fragments) {
                    fragment.updateBookList(bookList);
                }
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("Firebase", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });
//        reference.setValue(bookList);

    */

        ListLivres list = new ListLivres();
        Livre l = new Livre("Anna et Elsa", "Georges", "2016", "202", "1", "152");
        list.add(l);

        l = new Livre("Bob l'éponge", "Marie", "2016", "202", "2", "152");
        list.add(l);

        l = new Livre("Gasper le fantome", "Lise", "2016", "202", "3", "152");
        list.add(l);

        l = new Livre("mamie le chien", "marion", "2016", "202", "2", "152");
        list.add(l);

        l = new Livre("Leo le veau", "lea", "2016", "202", "1", "152");
        list.add(l);
        return list;
    }


    /*Navigation DRAWER */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list_lecture) {
            Intent intent = new Intent(MainActivity.this, ListGeneralActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sugestion) {
            Intent intent = new Intent(MainActivity.this, SuggestionActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_statistique) {
            Intent intent = new Intent(MainActivity.this, StatistiqueActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_rappel) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Connection a la data Base
    private void setConnection() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        mStatusTextView = (TextView) findViewById(R.id.logged_in_feedback);
        mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                            }
                        });
                updateUI(false);
            }
        });
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        mSignInButton.setSize(SignInButton.SIZE_STANDARD);
        mSignInButton.setScopes(gso.getScopeArray());
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        updateUI(false);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void updateUI(boolean isSigned) {
        if (isSigned) {
            mStatusTextView.setVisibility(View.VISIBLE);
            mSignOutButton.setVisibility(View.VISIBLE);
            mSignInButton.setVisibility(View.INVISIBLE);
        } else {
            mStatusTextView.setVisibility(View.INVISIBLE);
            mSignOutButton.setVisibility(View.INVISIBLE);
            mSignInButton.setVisibility(View.VISIBLE);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
//        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            firebaseAuthWithGoogle(acct);
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
