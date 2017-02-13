package xu.shallow.multi_listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import xu.shallow.multi_listview.Expandable.ExpandableLayoutListView;
import xu.shallow.multi_listview.R;
import xu.shallow.multi_listview.TransInfo;
import xu.shallow.multi_listview.Util;

/**
 * Created by Shallow Xu on 2017/2/5.
 */

public class Main_Adapter extends BaseAdapter {

	private Context context;
	private Map<String, List<TransInfo>> listMap;

	private String[] date_keys;

	public Main_Adapter(Context context, List<TransInfo> list) {
		this.context = context;
		getList(list);
	}

	/**
	 * 将交易记录按日期进行分组
	 *
	 * @param list 交易记录
	 */
	public void getList(List<TransInfo> list) {
		if (list == null || list.size() == 0) {
			date_keys = new String[0];
			return;
		}
		listMap = new HashMap<>();
		try {
			for (TransInfo transInfo : list) {
				String time = transInfo.getOrder_time();
				SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
				Date format_date = spf.parse(time);
				spf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
				String date = spf.format(format_date);
				if (listMap.containsKey(date)) {
					listMap.get(date).add(transInfo);
				} else {
					List<TransInfo> tmpList = new ArrayList<>();
					tmpList.add(transInfo);
					listMap.put(date, tmpList);
				}
			}
			date_keys = listMap.keySet().toArray(new String[listMap.size()]);
			Arrays.sort(date_keys, Collections.reverseOrder());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getCount() {
		return date_keys.length;
	}

	@Override
	public Object getItem(int position) {
		return date_keys[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.view_data_list_item, parent, false);
		}
		TextView data_list_title = BaseViewHolder.get(convertView, R.id.data_list_title);
		final ExpandableLayoutListView elv_data = BaseViewHolder.get(convertView, R.id.elv_data);

		data_list_title.setText(date_keys[position]);
		Data_List_Adapter adapter = new Data_List_Adapter(context, listMap.get(date_keys[position]));
		elv_data.setAdapter(adapter);
		Util.setListViewHeightBasedOnChildren(elv_data);

		return convertView;
	}

}
