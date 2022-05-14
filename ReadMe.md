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
/**
 * 获取当前用户所有地址
 * @return
 */
@GetMapping("/list")
public R<List<AddressBook>> list()
```

## @PostMapping

```
/**
 * 保存新地址
 * @param addressBook
 * @return
 */
@PostMapping
public R<AddressBook> save(@RequestBody AddressBook addressBook)
```

## /default

```
/**
 * 设置默认地址
 * @return
 */
@GetMapping("/default")
public R<AddressBook> getDefault()
```

## /default

```
/**
 * 更改默认地址
 * @param addressBook
 * @return
 */
@PutMapping("/default")
public R<AddressBook> setDefault(@RequestBody AddressBook addressBook)
```

## /{id}

```
/**
 * 根据id查找地址
 * @param id
 * @return
 */
@GetMapping("/{id}")
public R<AddressBook> getAdBook(@PathVariable Long id)
```

## @PutMapping

```
/**
 * 更新地址
 * @param addressBook
 * @return
 */
@PutMapping
public R<String> update(@RequestBody AddressBook addressBook)
```

## @DeleteMapping

```
/**
 * 删除地址
 * 可批量删除
 * @param ids
 * @return
 */
@DeleteMapping
public R<String> delete(@RequestParam List<Long> ids)
```

# /shoppingCart

## /list

```
/**
 * 查找当前用户购物车里的所有物品
 * @return
 */
@GetMapping("/list")
public R<List<ShoppingCart>> list()
```

## /add

```
/**
* 向购物车中添加物品
* @param shoppingCart
* @return
*/
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
/**
 * 查询所有订单信息/根据订单号查询
 * @param page
 * @param pageSize
 * @param number
 * @return
 */
@GetMapping("/page")
public R<Page> page(int page, int pageSize,Long number)
```

## /submit

```
/**
 * 订单提交
 * @param orders
 * @return
 */
@PostMapping("/submit")
public R<String> order(@RequestBody Orders orders)
```

## /userPage

```
/**
 * 查看用户历史订单
 * @param page
 * @param pageSize
 * @return
 */
@GetMapping("/userPage")
public R<Page> userPage(int page,int pageSize)
```

## @PutMapping

```
/**
 * 改变配送状态
 * @param orders
 * @return
 */
@PutMapping
public R<String> update(@RequestBody Orders orders)
```

## /{number}

```
/**
 * 根据订单号获取买卖家、商品信息、订单信息
 * @param number
 * @return
 */
@GetMapping("/{number}")
public R<OrdersDto> getDto(@PathVariable Long number)
```