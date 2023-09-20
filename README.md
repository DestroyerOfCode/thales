<p>to start app, use java 17 and docker-compose</p>
<p>go to setup and type docker-compose up -d</p>
<p>from source run ./gradlew clean build bootRun</p>
<h1>Example curl:</h1>
<p>curl -X POST -H "Content-Type: application/json" -d '[                                                                            [23:15:59]
    {
        "key": "transactionData.amount",
        "operation": "EQUALITY",
        "value": "100.00", "type": "MAP"
    }
]' http://localhost:8080/transactions/search
</p>

<p>you can search nested values (not lists) but must use type MAP. You can see operations in class TYPE.
If you want to compare greater or lesser number, use DOUBLE.
So far only and connections are possible, not or</p>