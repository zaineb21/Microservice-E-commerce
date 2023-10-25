const mongoose=require('mongoose')

const userSchema = new mongoose.Schema(
    {
        nom  :{ type:String,required:true,unique:true},
        prenom  :{ type:String,required:true,unique:true},
        email:{type:String,required:true,unique:true},
        password:{type:String,required:true},
        location  :{ type:String,required:true,unique:true},
        isAdmin:{type:Boolean,default:false},
        

        //remplace createdAt
    },{timestamps:true}
)
module.exports= mongoose.model("User",userSchema)