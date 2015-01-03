package cc.bb.aa.gradle_library_projects;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nineoldandroids.animation.ValueAnimator;

import aa.bb.cc.java_jar.Utils;
import cc.bb.aa.library.LibUtils;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 使用 jar
        Utils.f();

        // 使用 library
        LibUtils.f();

        // 使用 maven 。
        // 本例中使用到了两个maven依赖，一个是appcompat-v7，一个是NineOldAndroids。
        // 在这里简单使用一下NineOldAndroids。
        ValueAnimator animator = ValueAnimator.ofInt(0, 10);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                System.out.printf("" + animation.getAnimatedValue());
            }
        });
        animator.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
