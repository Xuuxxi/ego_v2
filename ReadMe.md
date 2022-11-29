# GET 方法使用 Restful 接收参数

**@GetMapping 获取**

**@PutMapping 更新**

**@DeleteMapping 删除**

**@PostMapping 新增**

* 无特殊路径则按此方法CRUD

# /user

## /login

```
R<User> login(HttpServletRequest request, @RequestBody User user)
```

## /logout

```
R<String> logOut(HttpServletRequest request)
```

## /register

```
R<String> register(@RequestBody User user)
```

## /{id}

```
R<UserDto> get(@PathVariable Long id)
```

# /common

## /upload

```
R<String> upload(MultipartFile file)
```

## /download

```
void download(String name, HttpServletResponse response)
```

# /good

## /{page}/{pageSize}/{name}

```
R<Page> page(@PathVariable int page,@PathVariable int pageSize,@PathVariable String name)
```

## /{id}

```
public R<GoodDto> getGood(@PathVariable Long id)
```

## /{goodId}

```
R<String> star(HttpServletRequest request,@PathVariable Long goodId)
```

## @PostMapping

```
R<String> save(@RequestBody Good good)
```

## @DeleteMapping

```
R<String> delete(@RequestParam List<Long> ids)
```

## @PutMapping

```
R<String> update(@RequestBody Good good)
```

# /trade(暂时弃用并通过order路径操作)

## /{id}

```
R<TradeDto> get(@PathVariable Long id)
```

## @PostMapping

```
R<String> save(@RequestBody Trade trade)
```

# /addressBook

## /list

```
// for dbs final test
@GetMapping("/list")
public R<List<AddressBook>> list()
```

## @PostMapping

```
// for dbs final test
@PostMapping
public R<AddressBook> save(@RequestBody AddressBook addressBook)
```

## /default

```
// for dbs final test
@GetMapping("/default")
public R<AddressBook> getDefault()
```

## /default

```
// for dbs final test
@PutMapping("/default")
public R<AddressBook> setDefault(@RequestBody AddressBook addressBook)
```

## /{id}

```
// for dbs final test
@GetMapping("/{id}")
public R<AddressBook> getAdBook(@PathVariable Long id)
```

## @PutMapping

```
// for dbs final test
@PutMapping
public R<String> update(@RequestBody AddressBook addressBook)
```

## @DeleteMapping

```
// for dbs final test
@DeleteMapping
public R<String> delete(@RequestParam List<Long> ids)
```

# /shoppingCart

## /list

```
// for dbs final test
@GetMapping("/list")
public R<List<ShoppingCart>> list()
```

## /add

```
// for dbs final test
@PostMapping("/add")
public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart)
```

## /clean

```
**
 * 清空购物车
 * @return
 */
@DeleteMapping("/clean")
public R<String> delete()
```

# /order

## /page

```
// for dbs final test
@GetMapping("/page")
public R<Page> page(int page, int pageSize,Long number)
```

## /submit

```
// for dbs final test
@PostMapping("/submit")
public R<String> order(@RequestBody Orders orders)
```

## /userPage

```
// for dbs final test
@GetMapping("/userPage")
public R<Page> userPage(int page,int pageSize)
```

## @PutMapping

```
// for dbs final test
@PutMapping
public R<String> update(@RequestBody Orders orders)
```

## /{number}

```
// for dbs final test
@GetMapping("/{number}")
public R<OrdersDto> getDto(@PathVariable Long number)
```