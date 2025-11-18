package plugin.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private int count;

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);


  }

  /**
   * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
   *
   * @param e イベント
   */
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
    // プレーヤーがスニークしてる時だけ(スニーク回数が偶数の時(割る2して余りが0のとき))
    if (count % 2 == 0) {

      // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
      Player player = e.getPlayer();
      World world = player.getWorld();

      // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
      Firework firework = world.spawn(player.getLocation(), Firework.class);

      // 花火オブジェクトが持つメタ情報を取得。
      FireworkMeta fireworkMeta = firework.getFireworkMeta();

      // メタ情報に対して設定を追加したり、値の上書きを行う。
      fireworkMeta.addEffect(
          FireworkEffect.builder()
              .withColor(Color.RED)
              .withColor(Color.BLUE)
              .with(Type.BALL_LARGE)
              .withFlicker()
              .build());
      fireworkMeta.setPower(1);

      // 追加した情報で再設定する。
      firework.setFireworkMeta(fireworkMeta);

      // 入出力処理
      // テキストファイルを開いて(なければ作成して)、文字列を書き込む(毎回上書きされる)
      Path path = Path.of("firwork.txt");
      Files.writeString(path, "たーまやー");
      //文字列を読み込んで、メッセージとして送る
      player.sendMessage(Files.readString(path));
    }
    count++;
  }
}
