'use strict';

// var userDAOImpl = require('../services/userDAOImpl');
// var User = require('../models/user');
var db = require('../services/db');
// var userDAO = new userDAOImpl(db);

exports.getTotalSales = function(req, res, next) {
	var start = req.param('start') || '2016-01-01';
  	var end = req.param('end') || '2018-01-01';
	var values = [start, end];
	let query = 'SELECT created_at as date, sum(price) as sales FROM orders WHERE created_at between ? and ?  group by created_at;'	
	db.get().query(query, values, function(err, results) {
		if (err) {
			res.send(JSON.stringify("Error"));
		}
		else {
			res.send(JSON.stringify({results}))
		};
	})
}

exports.topDishes = function(req, res, next) {
	var n = req.param('n') || 5;
	var start = req.param('start') || '2016-01-01';
  	var end = req.param('end') || '2018-01-01';
  	var values = [start, end, n];
	let query = 'SELECT b.name, a.total from (SELECT item_id, count(*) total from orders WHERE created_at between ? and ? group by item_id order by total desc limit ?) a left join items b on a.item_id = b.id ;';
	db.get().query(query, values, function(err, results) {
		if (err) {
			res.send(JSON.stringify("Error"));
		}
		else {
			res.send(JSON.stringify({results}))
		};
	})
}

exports.worstDishes = function(req, res, next) {
	var n = req.param('n') || 5;
	var start = req.param('start') || '2016-01-01';
  	var end = req.param('end') || '2018-01-01';
  	var values = [start, end, n];
	let query = 'SELECT b.name, a.total from (SELECT item_id, count(*) total from orders WHERE created_at between ? and ? group by item_id order by total asc limit ?) a left join items b on a.item_id = b.id ;';
	db.get().query(query, values, function(err, results) {
		if (err) {
			res.send(JSON.stringify("Error"));
		}
		else {
			res.send(JSON.stringify({results}))
		};
	})
}