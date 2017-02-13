package xu.shallow.multi_listview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Shallow Xu on 2017/2/11.
 */

public class Util {

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		try {
			ListAdapter listAdapter = listView.getAdapter();
			if (listAdapter == null) {
				// pre-condition
				return;
			}
			int totalHeight = 0;
			for (int i = 0; i < listAdapter.getCount(); i++) {
				if (i >= listView.getFirstVisiblePosition() && i <= listView.getLastVisiblePosition()) {
					totalHeight += listView.getChildAt(i).getMeasuredHeight();
				} else {
					View listItem = listAdapter.getView(i, null, listView);
					listItem.measure(0, 0);
					totalHeight += listItem.getMeasuredHeight();
				}
			}
			ViewGroup.LayoutParams params = listView.getLayoutParams();
			params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
			listView.setLayoutParams(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
