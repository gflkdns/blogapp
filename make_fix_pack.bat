cd ./app/build/intermediates/classes/debug/
jar cvf hotfix_pack.jar  ./com/iezview/sway2/proxy/BaseProxy.class ./com/iezview/sway2/proxy/WebVideoPlayerActyProxy.class ./com/iezview/sway2/proxy/ActionProxy.class
dx --dex --output=../../../../../hotfix_pack.dex hotfix_pack.jar
cd ../../../../../
