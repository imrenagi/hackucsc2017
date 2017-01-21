var express = require('express')
  , router = express.Router()
  , Message = require('../models/message')

router.use(function(req, res, next){
  res.setHeader('Content-Type', 'application/json');
  next()
})

router.get('/first', function(req, res){
  res.end(JSON.stringify("Hello World"))
})

module.exports = router
