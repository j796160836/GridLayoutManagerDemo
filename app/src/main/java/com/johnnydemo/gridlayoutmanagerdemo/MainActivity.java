package com.johnnydemo.gridlayoutmanagerdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

public class MainActivity extends ActionBarActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	// Reference
	// https://medium.com/android-bites/grid-layout-with-header-3f2966181c4d

	private ElementsAdapter elementsAdapter;
	private GridLayoutManager gridLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);

		elementsAdapter = new ElementsAdapter(generateFakeSectionData());
		gridLayoutManager = new GridLayoutManager(this, 2);
		gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int pos) {
				return (elementsAdapter.getItemViewType(pos) == ElementsAdapter.ElementsType.VIEW_HEADER) ? 2 : 1;
			}
		});
		recyclerView.setLayoutManager(gridLayoutManager);
		recyclerView.setAdapter(elementsAdapter);
	}

	private void refreshData() {
		elementsAdapter.setDatasetSizes(generateFakeSectionData());
		elementsAdapter.notifyDataSetChanged();
	}

	private int[] generateFakeSectionData() {
		int[] sectionData = Utils.generateFakeData(new Random().nextInt(10) + 3);
		Log.v(TAG, Utils.printArray(sectionData));
		return sectionData;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_refreseh:
				refreshData();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
