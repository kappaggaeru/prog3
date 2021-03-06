
## Notas:
Están ambos datasets con el path absoluto porque no me funcionaba haciéndolo relativo, el segundo está comentado, funcionan ambos pero el segundo tardó **15 minutos** en compilar teniendo la poda xD

## Análisis:
### Estado inicial:
Considero como estado inicial el recorrido de las familias, ya que cada familia sabe en cual de los días le gustaría visitar el taller. Tomando la primer familia probándola en su primer día preferido.
### Criterio de ramificación:
Siguiendo un criterio tal que todas las familias se las va a considerar asignarlas primero que las demás familias, ejemplo: se va a considerar que la cuarta familia sea la primera a la que se le asigne un día.
La ramificación se la puede ver como si esta estuviera al revés, tomando como ejemplo un dataset con 3 familias:

`1,1,1 -> 1,1,2 -> 1,1,3 -> 1,2,1 -> 1,2,2 -> 1,2,3 -> 1,3,1 -> 1,3,2 -> 1,3,3 ... n,n,n.`
### Profundidad del árbol:
La profundidad se mide teniendo en cuenta la cantidad de estados de cada familia, y el producto de que por cada estado de una familia existen n-1 estados posibles en las familias restantes.
### Estado final:
Cuando se consideraron todas las familias.
### Estado solución:
Cuando todas las familias tienen un día asignado.
### Restricciones:
Si una familia no logra ser asignada a cualquiera de sus días preferidos, impide la generación de nuevos estados, hasta que la vuelta de la recursión genere un espacio para dicha familia o se descarte esa rama.
En un estado se avanza la recursión asignando una familia a un día en particular y contemplando la suma de los bonos compensatorios hasta el momento, ambos datos se deshacen a la vuelta de la recursión.
### Poda:
La poda tiene como condición que el bono que se está generando actualmente no supere al menor bono encontrado hasta el momento, de forma que el algoritmo no explore ramas que no pertenezcan a la solución del problema.
#### Estados:

||Sin poda|Con poda|Solución
-|-|-|-
Dataset1|574076845|11573900|156127|
Dataset2|*|*|71987

*No lo hice porque tardaba mucho ;D

## Correcciones

 - Se eliminó la función *probarTodas*
 - Se modificó la condición de la poda desde **>** a **>=**
 - Se modificó el tipo de dato de la variable *estados* de un **int** a un **long**
 - Se agregó una condición para comprobar la capacidad del día antes de verificar si admite a una familia
 - Se cambió la línea dónde se hace la poda
 - La búsqueda de una familia se hace con un id en lugar de con un recorrido
 - La comprobación de que todas las familias fueron asignadas se realiza con un contador en lugar de con un recorrido

### Nuevos valores
||Sin poda|Con poda
|-|-|-
|Dataset 1|29.765.573|695.894
|Dataset 2|*|50.866.131

*Probablemente no termine

### Observaciones
La ejecución del backtracking para el segundo dataset ya no requiere una espera de 15 minutos, sino una espera de 15 segundos.
