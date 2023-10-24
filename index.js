const express = require('express')
const userRoute = require('./routes/user')
const authRoute =require('./routes/auth')
const app=express()
const eurekaHelper = require('./eurekaservice');
//connect to mongo  
const mongoose=require("mongoose")
mongoose.connect("mongodb+srv://mohamedaminebensalah:amine23@cluster0.uvopiha.mongodb.net/?retryWrites=true&w=majority")
   .then(()=>console.log('DBconnection succeful !!'))
    .catch((err)=>{console.log(err)})

//use json 
app.use(express.json()) 
//routes
app.use('/api/user',userRoute)
app.use('/api/auth',authRoute)
  
app.listen(5000,()=>{
        console.log("backend  server is running ")
})

eurekaHelper.registerWithEureka('user-service', 5000);