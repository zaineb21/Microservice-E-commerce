const user = require('../model/User')
const { verifyTokenAndAuthorization, verifyTokenAndAdmin } = require('./Verifytoken')


const router = require('express').Router()

//update
router.put('/:id', verifyTokenAndAuthorization,async (req,res)=>{
    if(req.body.password){
       req.body.password=Crypto.AES.encrypt(req.body.password,"amine").toString()
    }
    try{
        const updateuser=await user.findByIdAndUpdate(req.params.id,{
            $set:req.body
        },{new:true})
     res.status(200).json(updateuser)
    }catch(err){
        res.status(500).json(err)
    }

})
//Delete
router.delete('/:id',verifyTokenAndAuthorization,async (req,res)=>{
    try{
    await user.findByIdAndDelete(req.params.id)
    res.status(200).json('user has been deleted')
    }
    catch(err){
        res.status(500).json(err)
    }
})
//Get User 
router.get('/findid',verifyTokenAndAdmin,async (req,res)=>{
    try{
    const User=await user.findById(req.query.id)

    res.status(200).json(User )
    }
    catch(err){
        res.status(500).json(err)
    }
})
//Get All User 
router.get('/find',verifyTokenAndAdmin,async (req,res)=>{
    try{
    const Users=await user.find()
    res.status(200).json(Users )
    }
    catch(err){
        res.status(500).json(err)
    }
})
// get user stats 
router.get('/stats',verifyTokenAndAdmin,async (req,res)=>{
    const date =new Date();
    const lastYear=new Date(date.setFullYear(date.getFullYear()-1));

    try{
    const data= await user.aggregate([{$match:{createdAt:{$gte : lastYear}}},{$project:{month:{$month:"$createdAt"}}},{$group:{_id:"$month",total:{$sum:1}}}])  
      res.status(200).json(data)    
}catch(err){
        res.status(500).json(err)
    }
})
module.exports = router