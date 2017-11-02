package app.whowent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setUpButtonPresses();
    }

    //==============================================================================================
    // UI
    //==============================================================================================

    //---setUpButtonPresses-------------------------------------------------------------------------

    // creates onClick() functions for buttons
    void setUpButtonPresses() {
        final Context currentContext = this;
        Button publicButton = (Button)findViewById(R.id.edit_public_button);
        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(currentContext, EditPublicProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
