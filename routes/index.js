var express = require('express');
var router = express.Router();
var dataController = require('../controllers/dataController');

router.use(function(req, res, next){
  res.setHeader('Content-Type', 'application/json');
  next();
})

router.get('/', function(req, res) {
  res.render('index', {title:"HackUCSC"});
})

router.get('/sales', dataController.getTotalSales);
router.get('/topDishes', dataController.topDishes);
router.get('/worstDishes', dataController.worstDishes);

module.exports = router;