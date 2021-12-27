import * as React from 'react';
import { TouchableOpacity, View, Dimensions } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import FeedScreen from '../FeedScreen';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import  Constants from 'expo-constants';
import MapScreen from '../MapScreen';
import SearchScreen from '../SearchScreen';
import PostScreen from '../PostScreen';

const PostStack = createStackNavigator();

function PostStackScreen() {
  return (
    <View style ={{paddingTop: Constants.statusBarHeight, backgroundColor: "#080f26", height: Dimensions.get('window').height}}>
    <PostStack.Navigator>
      <PostStack.Screen name="Post" component={PostScreen} options={{
            headerTitle:"Post Report",
            headerStyle: {
              backgroundColor: "#080f26",
              height: 50
            },
            headerTintColor: '#fff',
            headerTitleStyle: {
            fontWeight: 'bold',
            },
            headerRight: () => (
              <TouchableOpacity onPress={() => alert('This is a button!')}>
                <MaterialCommunityIcons name="bell-alert" size={28} color="#cb7b23" style={{marginRight: 24 }}/>
              </TouchableOpacity>
            ),}}/>
    </PostStack.Navigator>
    </View>
  );
}

export default PostStackScreen;