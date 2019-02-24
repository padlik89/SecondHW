package com.example.secondhw;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String doc_count = "doc_count";
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentManager.getBackStackEntryCount() == 0) checkEnabled(false);
                else checkEnabled(true);
            }
        });
    }

    //сохранение данных при повороте экрана
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(doc_count, fragmentManager.getBackStackEntryCount());
    }


    //восстановление данных при повороте экрана
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    //создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        if(fragmentManager.getBackStackEntryCount() == 0)
        {
            checkEnabled(false);
        }
        return true;
    }

    //выбор добавить/удалить
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.remove) {
            onDeleteClicked();
            return true;
        }

        if (item.getItemId() == R.id.add) {
            onAddClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //кнопка добавить
    private void onAddClicked() {
        fragmentManager.beginTransaction()
                .replace(R.id.container, BlankFragment1.newInstance(fragmentManager.getBackStackEntryCount()+1))
                .addToBackStack(null)
                .commit();
    }

    //кнопка удалить
    private void onDeleteClicked() {
        fragmentManager.popBackStack();
    }

    //метод проверки активности кнопки удалить
    private void checkEnabled(Boolean isEnable) {
        MenuItem del = this.menu.findItem(R.id.remove);
        del.setEnabled(isEnable);
    }
}