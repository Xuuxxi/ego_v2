**GET 方法使用 Restful 接收参数**

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

# /trade

## /{id}

```
R<TradeDto> get(@PathVariable Long id)
```

## @PostMapping

```
R<String> save(@RequestBody Trade trade)
```