CREATE INDEX idx_orders_customer ON t_ch_orders(customer_id);
CREATE INDEX idx_orders_status ON t_ch_orders(status);
CREATE INDEX idx_order_items_order ON t_ch_order_items(order_id);
CREATE INDEX idx_order_status_history_order ON t_ch_order_status_history(order_id);