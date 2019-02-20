package com.example.secondhw;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String doc_count = "doc_count";
    private int docCount = 0;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MenuItem del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //сохранение данных при повороте экрана
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(doc_count, docCount);
    }


    //восстановление данных при повороте экрана
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        docCount = savedInstanceState.getInt(doc_count);
    }

    //создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        del = menu.getItem(1);
        checkEnabled();
        return super.onCreateOptionsMenu(menu);
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
        docCount++;
        checkEnabled();
        fragmentManager.beginTransaction()
                .add(R.id.container, BlankFragment1.newInstance(docCount))
                .replace(R.id.container, BlankFragment1.newInstance(docCount))
                .addToBackStack(null)
                .commit();
    }

    //кнопка удалить
    private void onDeleteClicked() {
        docCount--;
        fragmentManager.popBackStack();
        checkEnabled();
    }

    //переопределение кнопки "назад"
    @Override
    public void onBackPressed() {
        docCount--;
        fragmentManager.popBackStack();
        checkEnabled();
    }

    //проверка активности кнопки удалить
    private void checkEnabled() {
        if (docCount < 1) {
            del.setEnabled(false);
        } else {
            del.setEnabled(true);
        }
    }
}