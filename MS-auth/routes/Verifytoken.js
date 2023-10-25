//middleware
const jwt=require('jsonwebtoken')
/* 
const verifyToken=(req,res,next)=>{
    const authHeader=req.headers.token
    console.log(authHeader);
    if(authHeader){
        const token=authHeader.split(" ")[1];
        console.log(token)
        
      jwt.verify(token,"amine",(err,user)=>{
        
        if(err) 
          res.status(403).json("Token is not valid")
          req.user=user
         // const userId = req.user;
          //const userName = req.user.name;
          console.log(user)
          next()
      })
    }else{
        return res.status(401).json("you are not authenticated ")
    }
} */
const verifyToken = (req, res, next) => {
  const authHeader = req.query.token;
  console.log(authHeader); // move this line after the `if (authHeader)` statement

  if (authHeader) {
    const token = authHeader.split(' ')[1];
     console.log(token); // move this line after the `if (authHeader)` statement

    jwt.verify(token, 'amine', (err, user) => {
      if (err) {
        return res.status(403).json('Token is not valid');
      }

      req.user = user;

      next();
    });
  } else {
    return res.status(401).json('You are not authenticated');
  }
};

// Vous ne devriez pas afficher authHeader en dehors de la fonction verifyToken, car cela renverra undefined.

const verifyTokenAndAuthorization=(req,res,next)=>{
    verifyToken(req,res,()=>{
        if(req.user.id===req.params.id ){
        next()
        }
        else{
            res.status(403).json("you are not allowed to do that")
        }
    })
}

  const verifyTokenAndAdmin = (req, res, next) => {
    verifyToken(req, res, () => {
      console.log(req.user+"test user");
      if (req.user.isAdmin) {
        
        next()
      } else {
        res.status(403).json("You are not alowed to do that!");
      }
    });
  };
  
module.exports= {verifyToken,verifyTokenAndAuthorization,verifyTokenAndAdmin}