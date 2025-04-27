# pokemon demo
一个KMM项目，包含了Android和iOS的原生代码，使用Kotlin Multiplatform Mobile (KMM)来共享业务逻辑。

### OpenAPI 
* API采用OpenAPi进行代码生成调用`./gradlew openApiGenerate`,代码生成后有几个错误需要手动修改一下`EvolutionChainDetailChain`中`evolution_details`对象需要把Any修改成`EvolutionChainDetailChainEvolvesToInnerEvolutionDetailsInner`
