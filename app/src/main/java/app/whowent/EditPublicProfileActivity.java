package app.whowent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class EditPublicProfileActivity extends AppCompatActivity {

    static final int CAMERA_PIC_REQUEST = 1337;

    //UI
    ImageButton editProfilePicture;
    EditText editName;
    EditText editBio;

    //==============================================================================================
    // START
    //==============================================================================================
    /*
        Functions for when the Activity boots up
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_public_profile);

        setUpButtons();
    }

    //---setUpButtons-------------------------------------------------------------------------------

    // creates onClick() functions for buttons
    void setUpButtons() {

        editName = (EditText)findViewById(R.id.edit_name);
        editBio = (EditText)findViewById(R.id.edit_bio);


        editProfilePicture = (ImageButton)findViewById(R.id.edit_profile_pic);
        editProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open up camera
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra("android.intent.extras.USE_FRONT_CAMERA", true);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });

    }

    //---onActivityResults--------------------------------------------------------------------------

    // collects data from finished Activities
    // In this case, getting the picture taken by Camera activity for profile picture
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            if (data!= null) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                editProfilePicture.setImageBitmap(image);
            }
        }
    }


}
