## SQL CRUD Queries  
  
### Category  
  
Crear Category  
```sql  
INSERT INTO CATEGORY(CA_ID, CA_NAME, CA_DESCRIPTION, CA_ESTADO) VALUES (:id, :name, :description, :estado)  
```  
  
Buscar Category por ID  
```sql
SELECT * FROM CATEGORY WHERE CA_ID=:soughtId  
```  
  
Buscar todas las Category  
```sql
SELECT * FROM CATEGORY
```
  
Actualizar Category  
```sql
UPDATE CATEGORY SET CA_NAME=:name, CA_DESCRIPTION=:description WHERE CA_ID=:id
```
  
Borrar Category
```sql
DELETE FROM CATEGORY WHERE CA_ID=:id
```


### Product    
  
Crear Product  
```sql  
INSERT INTO PRODUCT(PR_ID, PR_NAME, PR_DESCRIPTION, ST_ID, CA_ID, CA_ESTADO, PR_CREATION_DATE) VALUES (:id, :name, :description, :stockId, :categoryId, :estado, :fechaCreacion)  
```  
  
Buscar Product por ID  
```sql
SELECT * FROM PRODUCT WHERE PR_ID=:soughtId
```
  
Buscar todos los Product  
```sql
SELECT * FROM PRODUCT
```
  
Actualizar Product  
```sql
UPDATE PRODUCT SET PR_NAME=:name, PR_DESCRIPTION=:description, ST_ID=:stockId, CA_ID=:categoryId, PR_MODIFICATION_DATE=:modDate WHERE PR_ID=:id
```
  
Delete lógico de Product  
```sql
UPDATE PRODUCT SET PR_ESTADO=0 WHERE PR_ID=:id
```
  
Delete Físico de Product  
```sql
DELETE FROM PRODUCT WHERE PR_ID=:id
```
  
### Stock  
  
Crear Stock  
```sql  
INSERT INTO STOCK(ST_ID, ST_QUANTITY, ST_LOCATION_CODE, ST_ESTADO) VALUES (:id, :quantity, :locationCode, :estado)  
```  
  
Buscar Stock por ID  
```sql
SELECT * FROM STOCK WHERE ST_ID=:soughtId  
```
  
Buscar todos los Stock  
```sql
SELECT * FROM STOCK
```
  
Actualizar Stock  
```sql
UPDATE STOCK SET ST_QUANTITY=:newQuantity, ST_LOCATION_CODE=:newLocationCode WHERE ST_ID=:id
```
  
Borrar Stock  
```sql
DELETE FROM STOCK WHERE ST_ID=:id
```
