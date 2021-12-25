import React from "react";
import {
  Image,
  StyleSheet,
  Text,
  View,
  TextInput,
  TouchableOpacity,
  SafeAreaView,
  StatusBar,
  Keyboard,
  Linking,
  Platform,
  KeyboardAvoidingView,
  ScrollView,
  TouchableWithoutFeedback,
} from "react-native";
import BouncyCheckbox from "react-native-bouncy-checkbox";


export default class CitizenRegistrationForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      checked1: false,
    };
    
    this._isMounted = false;
  }
  render() {
    return (
      <KeyboardAvoidingView>
        <ScrollView>
          <TouchableWithoutFeedback>
            <SafeAreaView
              style={{
                width: "100%",
                height: "100%",
                backgroundColor: "#080f26",
                paddingTop:
                  Platform.OS === "android" ? StatusBar.currentHeight * 2 : 0,
              }}
            >
              <View style={styles.regForm}>
                <View style={{ height: "20%", alignItems: "center" }}>
                  <Image
                    source={require("../gui_components/reportown.png")}
                    style={{
                      width: "60%",
                      height: "100%",
                      resizeMode: "contain",
                      backgroundColor: "#080f26",
                    }}
                  />
                </View>
                <Text style={styles.header}>Register</Text>
                <TextInput
                  style={styles.textinput}
                  placeholder="Name"
                  underlineColorAndroid={"transparent"}
                  placeholderTextColor={"gray"}
                />

                <TextInput
                  style={styles.textinput}
                  placeholder="Surname"
                  underlineColorAndroid={"transparent"}
                  placeholderTextColor={"gray"}
                />

                <TextInput
                  style={styles.textinput}
                  placeholder="Username"
                  underlineColorAndroid={"transparent"}
                  placeholderTextColor={"gray"}
                />

                

                <TextInput
                  style={styles.textinput}
                  placeholder="Password"
                  secureTextEntry={true}
                  underlineColorAndroid={"transparent"}
                  placeholderTextColor={"gray"}
                />

                <TextInput
                  style={styles.textinput}
                  placeholder="Password (Again)"
                  secureTextEntry={true}
                  underlineColorAndroid={"transparent"}
                  placeholderTextColor={"gray"}
                />

            <View
                style={{ width: "90%", alignItems: "center", paddingTop: "5%" }}
              >
                <View
                  style={{
                    width: "100%",
                    flexDirection: "row",
                    justifyContent: "center",
                    alignItems: "center",
                  }}
                >
                  <BouncyCheckbox
                    fillColor="#cb7b23"
                    isChecked={this.state.checked1}
                    disableBuiltInState
                    onPress={() => {
                        this.setState({ checked1: !this.state.checked1 });
                    }}
                    style={{ width: "8.5%", aspectRatio: 1 }}
                  />
              
                    <Text
                      style={{ color: "#cb7b23", fontWeight: "bold" }}
                      onPress={() =>
                        Linking.openURL("https://passmiracle.com/kvvk.pdf")
                      }
                    >
                      I understand and accept terms of usage.
                    </Text>
                </View>
              </View>

                <TouchableOpacity style={styles.button}>
                  <Text style={styles.buttontext}>Register</Text>
                </TouchableOpacity>
              </View>
            </SafeAreaView>
          </TouchableWithoutFeedback>
        </ScrollView>
      </KeyboardAvoidingView>
    );
  }
}

const styles = StyleSheet.create({
  regForm: {
    backgroundColor: "#080f26",
  },
  header: {
    fontSize: 24,
    color: "#fff",
    paddingBottom: 10,
    marginBottom: 40,
    alignItems: "center",
    textAlign: "center",
    paddingTop: 30,
  },
  textinput: {
    alignSelf: "stretch",
    height: 40,
    marginLeft: 30,
    marginRight: 30,
    paddingLeft: 10,
    paddingRight: 10,
    marginBottom: 30,
    color: "#fff",
    backgroundColor: "white",
    borderColor: "#f8f8f8",
    borderWidth: 1,
    borderRadius: 5,
  },
  button: {
    alignItems: "center",
    padding: 20,
    backgroundColor: "#cb7b23",
    marginBottom: 250,
    width: 200,
    borderRadius: 5,
    marginLeft: "25%",
  },
  buttontext: {
    color: "#fff",
    fontWeight: "bold",
  },
});
