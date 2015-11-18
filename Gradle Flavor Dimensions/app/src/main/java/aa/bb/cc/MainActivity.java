package aa.bb.cc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textview);
        textView.append("\nAppName = " + getString(R.string.app_name));
        textView.append("\nBuildTypesName = " + BuildTypesUtils.getName());
        textView.append("\nStore = " + Store.getName());
        textView.append("\nPrice = " + Price.getName());
        textView.append("\nPackageName = " + getPackageName());
    }
}
