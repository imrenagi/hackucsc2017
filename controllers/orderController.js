'use strict';

var db = require('../services/db');

exports.createOrder = function(req, res, next) {
	var tableNumber = req.body.table_number;
	var items = req.body.items;
	var qty = req.body.qty;
	var values = [tableNumber, items, qty];
	let query = 'insert into food_orders (`table_num`, `items`, `quantity`) values (?, ?, ?);'	
	db.get().query(query, values, function(err, results) {
		if (err) {
			res.send(JSON.stringify("Error"));
		}
		else {
			res.send(JSON.stringify({results}))
		};
	})
}
