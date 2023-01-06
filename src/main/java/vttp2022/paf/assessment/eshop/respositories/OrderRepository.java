package vttp2022.paf.assessment.eshop.respositories;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean insertPurchaseOrder(Order o) {
		return jdbcTemplate.update(SQL_INSERT_PURCHASE_ORDER, o.getOrderId(), o.getName(), o.getOrderDate()) > 0;
	}

	public void addLineItems(Order o) {
		addLineItems(o.getLineItems(), o.getOrderId());
	}

	public void addLineItems(List<LineItem> lineItems, String orderId) {
		List<Object[]> data = lineItems.stream()
			.map(li -> {
				Object[] l = new Object[2];
				l[0] = li.getItem();
				l[1] = li.getQuantity();
				return l;
			})
			.toList();

			//? Batch update
			jdbcTemplate.batchUpdate(SQL_INSERT_LINE_ITEM, data);
	}




}
