var window = floaty.window(
    <vertical h="200pd">
        <button layout_weight="1" id="change">adjust</button>
        <horizontal layout_weight="1">
            <button id="but1">音量+</button>
            <button id="but2">音量-</button>
            <button id="but6">一键静音按钮</button>
        </horizontal>
        <horizontal layout_weight="1">
            <button id="but3">亮度+</button>
            <button id="but4">亮度-</button>
            <button id="but5">自动亮度开关</button>
        </horizontal>
        <button layout_weight="1" id="but7">锁定屏幕</button>
    </vertical>
)

window.exitOnClose();

window.change.click(function(){
    window.setAdjustEnabled(!window.isAdjustEnabled());
})

window.but1.click(function(){
    device.setMusicVolume(device.getMusicVolume() + device.getMusicMaxVolume() / 10);
})

window.but2.click(function(){
    device.setMusicVolume(device.getMusicVolume() - device.getMusicMaxVolume() / 10);
})

window.but3.click(function(){
    if(device.getBrightnessMode() === 1) toast("当前为自动亮度~");
    else device.setBrightness(Math.min(device.getBrightness() + 20, 255));
})

window.but4.click(function(){
    if(device.getBrightnessMode() === 1) toast("当前为自动亮度~");
    else device.setBrightness(Math.max(device.getBrightness() - 20, 0));
})

window.but5.click(function(){
    device.setBrightnessMode((device.getBrightnessMode() + 1) % 2);
})

window.but6.click(function(){
    device.setMusicVolume(0);
})

window.but7.click(function(){
    device.setBrightnessMode(0);
    device.setBrightness(0);
})

setInterval(() => {}, 1000);