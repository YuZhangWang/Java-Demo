package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssessmentService {

	private List list = new ArrayList();

	// 添加一项综测
	public void insert(Assessment assessment) {
		System.out.println(assessment.getId());
		System.out.println(assessment.getYear());
		System.out.println(assessment.getTerm());
		list.add(assessment);
	}

	// 删除一项综测
	public int delete(Assessment assessment) {
		int flag = 0;
		int i = 0;

		String id = assessment.getId();
		String year = assessment.getYear();
		String term = assessment.getTerm();

		Iterator it = list.iterator();

		while (it.hasNext()) {
			assessment = (Assessment) it.next();
			if (assessment.getId().equals(id)
					&& assessment.getYear().equals(year)
					&& assessment.getTerm().equals(term)) {
				list.remove(i);
				flag = 1;
				break;
			}
			i++;
		}

		return flag;
	}

	// 修改一项综测
	public int update(Assessment assessment) {
		String id = assessment.getId();
		String year = assessment.getYear();
		String term = assessment.getTerm();

		int i = 0;
		int flag = 0;
		Iterator it = list.iterator();

		Assessment assess = null;
		while (it.hasNext()) {
			assess = (Assessment) it.next();
			if (assess.getId().equals(id)
					&& assess.getYear().equals(year)
					&& assess.getTerm().equals(term)) {

				list.remove(i);
				System.out.println(list.isEmpty());
				list.add(i, assessment);

				flag = 1;
			}
			i++;
		}
		return flag;
	}

	// 查询综测
	public List select(Assessment assessment) {
		String id = assessment.getId();
		String year = assessment.getYear();
		String term = assessment.getTerm();

		List selectList = new ArrayList();

		Iterator it = list.iterator();
		while (it.hasNext()) {
			assessment = (Assessment) it.next();
			if ((id.trim().equals("") || assessment.getId().equals(id))
					&& assessment.getYear().equals(year)
					&& (term.equals("0") || assessment.getTerm().equals(term))) {
				selectList.add(assessment);
			}
		}

		return selectList;
	}

}
