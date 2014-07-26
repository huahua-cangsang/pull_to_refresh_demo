package com.example.test1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends Activity {
	
	private ArrayList<String> mListItems;
    private PullToRefreshListView mListView;
    private ArrayAdapter<String> mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ���ó�������
        mListItems = new ArrayList<String>();
    	for (int i = 1; i <= 10; i++) {
    		mListItems.add("Item " + Integer.toString(i));
    	}

        // ����ListView
        mListView = (PullToRefreshListView) findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, mListItems);
        mListView.setAdapter(mAdapter);
        
        // ����PullToRefresh
        mListView.setMode(Mode.BOTH);
        mListView.setOnRefreshListener(new OnRefreshListener2<ListView>(){
 
        	// ����Pulling Down
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            	// ������ʱ����������
               /* mListItems = new ArrayList<String>();
            	for (int i = 1; i <= 10; i++) {
            		mListItems.add("Item " + Integer.toString(i));
            	}*/
            	mAdapter.notifyDataSetChanged();
            	
                new FinishRefresh().execute();
            }
            
            // ����Pulling Up
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            	// ������ʱ�����ѡ��
                int count = mListItems.size();
                mListItems.add("Item " + Integer.toString(++count));
                mAdapter.notifyDataSetChanged();
                
                new FinishRefresh().execute();
            }
 
        });
        
    }
    
   /* private class SampleListAdapter extends BaseAdapter {
    	 
        @Override
        public int getCount() {
            return mListItems.size();
        }
 
        @Override
        public Object getItem(int index) {
            return mListItems.get(index);
        }
 
        @Override
        public long getItemId(int index) {
            return index;
        }
 
        @Override
        public View getView(int index, View view, ViewGroup arg2) {
            if(view == null){
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, null);
            }
            TextView textView = (TextView)view.findViewById(R.id.listItemText);
            textView.setText(mListItems.get(index));
            return view;
        }
    }
 */
    private class FinishRefresh extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result){
            mListView.onRefreshComplete();
        }
    }
}
