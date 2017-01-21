var express = require('express')
  , router = express.Router()
  , Message = require('../models/message')

router.use(function(req, res, next){
  res.setHeader('Content-Type', 'application/json');
  next()
})

router.get('/first', function(req, res){
  var limit = req.param('limit') || 50;
  Message.getLatestMessages(limit, function(err, data){
    if (err) res.status(500).send(JSON.stringify({error:'error gan'}))
    res.end(JSON.stringify(data))
  })
})

router.get('/', function(req, res) {
  var id = req.param('id') ;
  var limit = req.param('limit') || 50;
  Message.getPreviousMessages(id, limit, function(err, data) {
  	res.send(JSON.stringify(data))
  })
})

router.post('/', function(req, res){
	var user_name = req.body.user_name
	var text = req.body.text
	var timestamp = new Date()
  if (user_name === "" || text === "") {
    res.status(400).send(JSON.stringify({error: 'message/user name should not be empty!'}))
  } else {
    Message.store(user_name, text, timestamp, function(err, data){
      res.end(JSON.stringify(data))
    })    
  }
})

module.exports = router
