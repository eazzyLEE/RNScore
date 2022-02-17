import React, {useState} from 'react';
import {Button, NativeModules, StyleSheet, Text, View} from 'react-native';

const {SCSNative} = NativeModules;

const App = () => {
  const [score, setScore] = useState('');
  console.log('ss', SCSNative);

  const scsCall = async () => {
    let authKey = 'ed7482b8-22ce-45c4-b49c-f8e42321ea7a';
    let userEmail = 'main@Xmail.com';

    //let scsApiURL = '';
    //let offerCode = '';
    //let phonenumber = '';
    scoreRes = await SCSNative.score(authKey, userEmail);
    Alert.alert(scoreRes);
  };
  return (
    <View style={styles.background}>
      <Text>Here we go!</Text>

      <Text>Click the button below to view SCS data</Text>
      <Text style={styles.score}>{score}</Text>
      <Button title="Trigger" onPress={scsCall} />
    </View>
  );
};

const styles = StyleSheet.create({
  background: {
    flex: 1,
    alignItems: 'center',
  },
  score: {
    fontSize: 18,
  },
});
export default App;
