import React, { useState, useEffect, useRef } from 'react';
import { Text, View, TouchableOpacity,Image } from 'react-native';
import { Camera } from 'expo-camera';
import { useIsFocused, useNavigation } from '@react-navigation/native';


export default function CameraScreen(props) {
  const [hasPermission, setHasPermission] = useState(null);
  
  const [type, setType] = useState(Camera.Constants.Type.back);
  const ref = useRef(null)
  const isFocused = useIsFocused();
  const navigation = useNavigation();


  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setHasPermission(status === 'granted');
    })();
  }, []);
  
  _takePhoto = async () => {
    const photo = await ref.current.takePictureAsync()
    console.debug(photo)
    navigation.goBack();
  }

  if (hasPermission === null) {
    return <View />;
  }
  if (hasPermission === false) {
    return <Text>No access to camera</Text>;
  }
  return (
    <View style={{ flex: 1 }}>
       { isFocused && <Camera style={{ flex: 1 }} type={type} ref={ref}>
       <View
          style={{
            flex: 0,
            backgroundColor: 'transparent',
            flexDirection: 'row',
            alignItems: 'center',
            justifyContent: "center"
          }}>

      <TouchableOpacity
            style={{
              marginTop:"5%",
              flex: 1,
              alignSelf: 'flex-start',
              alignItems: 'flex-start',
            }}
            onPress={() => {
              setType(
                type === Camera.Constants.Type.back
                  ? Camera.Constants.Type.front
                  : Camera.Constants.Type.back
              );
            }}>
             <Image source={require("../assets/flippng.png")} style={{width: 100, height: 100}} />
          </TouchableOpacity>
          </View>
        <View
          style={{
            flex: 1,
            backgroundColor: 'transparent',
            flexDirection: 'row',
            alignItems: 'center',
            justifyContent: "center"
          }}>
          
          <TouchableOpacity
          style={{
            flex: 1,
            alignSelf: 'flex-end',
            alignItems: 'center',
          }}
            onPress={_takePhoto}
          >
             <Image source={require("../assets/cam.png")} style={{width: 100, height: 100}} />
          </TouchableOpacity>

        </View>
      </Camera>}
      
    </View>
  );
}
