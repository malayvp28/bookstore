import mysql.connector
from flask import Flask, jsonify, request
from flask_cors import CORS

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  database="map_data"
)


#mycursor = mydb.cursor()
#x=request.forms.get('name')

#val = ('john',)
#sql = "INSERT INTO subject (name) VALUES (%s)"


#mycursor.execute(sql, val)

#mydb.commit()
#print(mycursor.rowcount, "record inserted.")

app=Flask(__name__)
CORS(app)
@app.route('/',methods=['POST'])
def Add():
	x=request.get_json()
	
	mycursor = mydb.cursor()
	
	
	sql = "SELECT qnty , cid FROM `cart` WHERE book_id=%s and cuser=%s "
	val = (x["bid"],x["cuser"])
	
	mycursor.execute(sql,val)
	re={}
	myresult = mycursor.fetchall()
	
	print("POST/")
	if(len(myresult)==0):
		sql = "INSERT INTO `cart`(`book_id`, `cuser`, `qnty`) VALUES (%s,%s,%s)"
		val = (x["bid"],x["cuser"],x["qnty"])
		
		mycursor.execute(sql,val)
		
		
	else:
		
		sql = "UPDATE `cart` SET qnty=%s WHERE cid=%s"
		val = (myresult[0][0]+1,myresult[0][1])
		mycursor.execute(sql,val)
		mydb.commit()
		import json
	y1 = '{ "message":"Item Added to Cart !!", "status":true}'
	import json
	y = json.loads(y1)
	return y 

@app.route('/gets/<user>',methods=['GET'])
def get(user):
	x=request.args.get('email')
	y=request.args.get('pass')
	
	mycursor = mydb.cursor()
	val = (x,y,)
	sql = "SELECT * FROM `cart` NATURAL JOIN `book` WHERE cuser = '"+user+"'"

	mycursor.execute(sql)
	myresult = mycursor.fetchall()
	print("GET/gets")
	mydb.commit()
	return jsonify(myresult) 
	
@app.route('/update/<id>/<status>',methods=['PUT'])
def update(id , status):
	
	
	mycursor = mydb.cursor()
	print(id)
	print(status)
	if(status == "true"):
		sql = "SELECT `qnty` FROM `cart` WHERE cid = "+str(id)
		
		mycursor.execute(sql)
		myresult = mycursor.fetchall()
		print(myresult[0][0])
		sql = "UPDATE `cart` SET `qnty`=%s WHERE cid=%s"
		val = (myresult[0][0]+1,id)
		mycursor.execute(sql,val)
		mydb.commit()
	else:
		sql = "SELECT `qnty` FROM `cart` WHERE cid = "+str(id)
		
		mycursor.execute(sql)
		myresult = mycursor.fetchall()
		print(myresult[0][0])
		if(myresult[0][0]>1):
			sql = "UPDATE `cart` SET `qnty`=%s WHERE cid=%s"
			val = (myresult[0][0]-1,id)
			mycursor.execute(sql,val)
			mydb.commit()
		
	return jsonify(myresult) 



@app.route('/delete/<cid>',methods=['DELETE'])
def deleteone(cid):
	
	mycursor = mydb.cursor()
	print("delete/////////////")
	sql = "DELETE FROM `cart` WHERE cid="+cid
	
	mycursor.execute(sql)
	mydb.commit()
	print("delete/success////")
		
	return jsonify()
app.run(debug=True)	
