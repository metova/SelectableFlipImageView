package com.metova.selectableflipimageviewexample;

import com.metova.selectableflipimageview.SelectableFlipImageView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.flip_view)
    SelectableFlipImageView mFlipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        initViews();
    }

    @OnClick(R.id.flip_button)
    public void onFlipButtonClicked(View v) {

        mFlipView.flip();
    }

    @OnClick(R.id.toggle_select_button)
    public void onToggleSelectButtonClicked(View v) {

        mFlipView.setCheckSelected(!mFlipView.isCheckSelected());

        if(mFlipView.isCheckSelected()) {

            ((Button) v).setText("UnSelect");
        }
        else {

            ((Button) v).setText("Select");
        }
    }

    private void initViews() {

        mFlipView.setSelectedDrawable(getResources().getDrawable(R.mipmap.ic_camera_rear_blue_48dp));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
