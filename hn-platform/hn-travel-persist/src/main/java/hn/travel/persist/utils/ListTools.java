package hn.travel.persist.utils;

import java.util.List;

import com.google.common.collect.Lists;

public class ListTools {

	public static <T> List<List<T>> splitList4Thread(List<T> list, int threadCount) {
		List<List<T>> result = Lists.newLinkedList();
		if(list.size() < threadCount*2) {
			result.add(list);
		} else {
			int ts = threadCount;
			int pts = list.size() / ts;
			if(list.size() % ts != 0) {
				ts = ts+1;
			}
			for(int i=0; i<ts; i++) {
				int s = i*pts-1;
				int e = (i+1)*pts-1;
				if(s>=list.size())
					s = list.size()-1;
				if(s<0)
					s = 0;
				if(e>=list.size())
					e = list.size()-1;
				List<T> part = list.subList(s, e);
				result.add(part);
			}
		}
		return result;
	}
}
